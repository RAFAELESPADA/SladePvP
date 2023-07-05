package com.besaba.revonline.pastebinapi.impl.user.internal;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.besaba.revonline.pastebinapi.impl.xml.UserXmlHandler;
import com.besaba.revonline.pastebinapi.user.User;

import lombok.NonNull;

public class Users {
  @NonNull
  public static User buildFromXmlResponse(@NonNull final String response)
      throws ParserConfigurationException, SAXException, IOException {
    final UserXmlHandler userHandler = new UserXmlHandler();
    SAXParserFactory
        .newInstance()
        .newSAXParser()
        .parse(
            new InputSource(new StringReader(response)),
            userHandler
        );
    return userHandler.getUser();
  }
}