package com.besaba.revonline.pastebinapi.impl;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.besaba.revonline.pastebinapi.Pastebin;
import com.besaba.revonline.pastebinapi.impl.user.internal.Users;
import com.besaba.revonline.pastebinapi.internal.utils.HttpEndpointConnection;
import com.besaba.revonline.pastebinapi.paste.Paste;
import com.besaba.revonline.pastebinapi.paste.internal.Pastes;
import com.besaba.revonline.pastebinapi.response.Response;
import com.besaba.revonline.pastebinapi.response.Responses;
import com.besaba.revonline.pastebinapi.user.User;

import lombok.NonNull;

public class PastebinImpl implements Pastebin {
  public static final int DEFAULT_NUMBER_OF_PASTES = 50;
  @NonNull
  private final String devKey;

  public PastebinImpl(@NonNull final String devKey) {
    this.devKey = devKey;
  }

  @NonNull
  @Override
  public Response<String> post(@NonNull final Paste paste) {
    return post(paste, null);
  }

  @NonNull
  @Override
  public Response<String> post(@NonNull final Paste paste, @Nullable final String userKey) {
    final HttpEndpointConnection<String> endpointConnection = HttpEndpointConnection.connectToMainEndpoint();
    endpointConnection.addParameter("api_option", "paste");
    endpointConnection.addParameter("api_dev_key", devKey);
    endpointConnection.addParameter("api_paste_code", paste.getRaw().get());
    endpointConnection.addParameter("api_paste_name", paste.getTitle());
    endpointConnection.addParameter("api_paste_format", paste.getMachineFriendlyLanguage());
    endpointConnection.addParameter("api_paste_private", String.valueOf(paste.getVisiblity().getValue()));
    endpointConnection.addParameter("api_paste_expire_date", paste.getExpire().getValue());

    if (userKey != null) {
      endpointConnection.addParameter("api_user_key", userKey);
    }

    return endpointConnection.executeAsPost();
  }

  @NonNull
  @Override
  public Response<List<Paste>> getTrendingPastes() {
    final HttpEndpointConnection<String> endpointConnection = HttpEndpointConnection.connectToMainEndpoint();
    endpointConnection.addParameter("api_option", "trends");
    endpointConnection.addParameter("api_dev_key", devKey);

    final Response<String> responseResult = endpointConnection.executeAsPost();

    if (responseResult.hasError()) {
      return Responses.failed(responseResult.getError());
    }

    try {
      final String rawResponse = responseResult.get();
      final String xmlResponse = "<root>" + rawResponse + "</root>";

      return Responses.success(Pastes.getPastesFromXmlResponse(xmlResponse));
    } catch (final SAXException | IOException | ParserConfigurationException e) {
      return Responses.failed("Unable to prepare/parse the XML response");
    }
  }

  @NonNull
  @Override
  public Response<String> getRawPaste(final String pasteKey) {
    return Pastes.download(pasteKey);
  }

  @NonNull
  @Override
  public Response<String> login(@NonNull final String userName, @NonNull final String password) {
    final HttpEndpointConnection<String> loginConnection = HttpEndpointConnection.connectToLoginEndpoint();
    loginConnection.addParameter("api_dev_key", devKey);
    loginConnection.addParameter("api_user_name", userName);
    loginConnection.addParameter("api_user_password", password);
    return loginConnection.executeAsPost();
  }

  @NonNull
  @Override
  public Response<User> getUser(@NonNull final String userKey) {
    final HttpEndpointConnection<String> userInformation = HttpEndpointConnection.connectToMainEndpoint();
    userInformation.addParameter("api_option", "userdetails");
    userInformation.addParameter("api_dev_key", devKey);
    userInformation.addParameter("api_user_key", userKey);

    final Response<String> requestResult = userInformation.executeAsPost();

    if (requestResult.hasError()) {
      return Responses.failed(requestResult.getError());
    }

    try {
      return Responses.success(
          Users.buildFromXmlResponse(requestResult.get())
      );
    } catch (final ParserConfigurationException | IOException | SAXException e) {
      return Responses.failed(e.getMessage());
    }
  }

  @NonNull
  @Override
  public Response<List<Paste>> getPastesOf(@NonNull final String userKey) {
    return getPastesOf(userKey, DEFAULT_NUMBER_OF_PASTES);
  }

  @NonNull
  @Override
  public Response<List<Paste>> getPastesOf(@NonNull final String userKey, final int limit) {
    final HttpEndpointConnection<String> userPastes = HttpEndpointConnection.connectToMainEndpoint();
    userPastes.addParameter("api_option", "list");
    userPastes.addParameter("api_dev_key", devKey);
    userPastes.addParameter("api_user_key", userKey);
    userPastes.addParameter("api_results_limit", String.valueOf(limit));

    final Response<String> pastesRequestResponse = userPastes.executeAsPost();

    if (pastesRequestResponse.hasError()) {
      return Responses.failed(pastesRequestResponse.getError());
    }

    final String rawResponse = pastesRequestResponse.get();

    if (rawResponse.contains("Bad API request")) {
      return Responses.failed(rawResponse);
    }

    if ("No pastes found.".equals(rawResponse)) {
      return Responses.success(Collections.<Paste>emptyList());
    }

    final String xmlResponse = "<root>" + rawResponse + "</root>";
    try {
      return Responses.success(
          Pastes.getPastesFromXmlResponse(xmlResponse)
      );
    } catch (final SAXException | IOException | ParserConfigurationException e) {
      return Responses.failed(e.getMessage());
    }
  }

  @Override
  public Response<Boolean> deletePaste(@NonNull final String pasteKey, @NonNull final String userKey) {
    final HttpEndpointConnection<String> deleteConnection = HttpEndpointConnection.connectToMainEndpoint();
    deleteConnection.addParameter("api_option", "delete");
    deleteConnection.addParameter("api_dev_key", devKey);
    deleteConnection.addParameter("api_paste_key", pasteKey);
    deleteConnection.addParameter("api_user_key", userKey);

    final Response<String> deleteRequest = deleteConnection.executeAsPost();

    if (deleteRequest.hasError()) {
      return Responses.failed(deleteRequest.getError());
    }

    final String rawResponse = deleteRequest.get();

    if (rawResponse.contains("Bad API request")) {
      return Responses.failed(rawResponse);
    }

    if ("Paste Removed".equals(rawResponse)) {
      return Responses.success(true);
    }

    return Responses.failed(rawResponse);
  }
}
