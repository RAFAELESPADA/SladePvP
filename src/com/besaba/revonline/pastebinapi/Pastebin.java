package com.besaba.revonline.pastebinapi;

import java.util.List;

import javax.annotation.Nullable;

import com.besaba.revonline.pastebinapi.paste.Paste;
import com.besaba.revonline.pastebinapi.response.Response;
import com.besaba.revonline.pastebinapi.user.User;

import lombok.NonNull;

/**
 * Every class which implements this interface should provide a way
 * to connect to Pastebin API.
 *
 */
public interface Pastebin {
  /**
   * Should post the Paste passed as parameter in Pastebin.
   *
   * @param paste The paste to share
   * @return The result of the request to post the paste. If everything is OK, it should return the paste url (or key)
   */
  @NonNull
  public Response<String> post(@NonNull final Paste paste);

  /**
   * Should post the Paste passed as parameter in Pastebin using the userKey passed
   * as parameter if not null.
   *
   * The user key should be used to assign the Paste to the user.
   *
   * @param paste The paste to share
   * @param userKey The userKey to use if not null.
   * @return The result of the request to post the paste. If everything is OK, it should return the paste url (or key)
   */
  @NonNull
  public Response<String> post(@NonNull final Paste paste, @Nullable final String userKey);

  /**
   * @return Should read and return pastebin trending pastes
   */
  @NonNull
  public Response<List<Paste>> getTrendingPastes();

  /**
   * @param pasteKey The paste key
   * @return Should read the paste raw code and return it
   */
  @NonNull
  public Response<String> getRawPaste(final String pasteKey);

  /**
   * Should try to login the user using the credentials passed as argument.
   *
   * @param userName The username
   * @param password The password
   * @return The request to login the user. If everything is OK, it should contains the user_key
   */
  @NonNull
  public Response<String> login(@NonNull final String userName, @NonNull final String password);

  /**
   * Should read the information of the user represented by the userKey passed as argument
   *
   * @param userKey User key
   * @return The request to get user information. If everything is OK, it should return an User
   * object contains the user informations.
   */
  @NonNull
  public Response<User> getUser(@NonNull final String userKey);

  /**
   * Should read all the pastes of the user represented by the userKey passed as
   * argument.
   *
   * @param userKey The user key
   * @return The request to read user pastes. If everything is OK, it should return a List
   * contains all the user pastes. If the user doesn't have any paste, it should return
   * an empty list.
   */
  @NonNull
  public Response<List<Paste>> getPastesOf(@NonNull final String userKey);

  /**
   * Should read all the pastes of the user represented by the userKey passed as
   * argument.
   *
   * @param userKey The user key
   * @param limit How many items it should return
   * @return The request to read user pastes. If everything is OK, it should return a List
   * contains all the user pastes. If the user doesn't have any paste, it should return
   * an empty list.
   */
  @NonNull
  public Response<List<Paste>> getPastesOf(@NonNull final String userKey, final int limit);

  /**
   * Should ask to Pastebin to delete the paste assigned to the pasteKey passed as argument.
   *
   * The userKey should be used to check if the user can delete the paste.
   *
   * @param pasteKey Paste key.
   * @param userKey User key.
   * @return The request to delete the paste. If everything is OK it will contains true.
   */
  public Response<Boolean> deletePaste(@NonNull final String pasteKey, @NonNull final String userKey);
}
