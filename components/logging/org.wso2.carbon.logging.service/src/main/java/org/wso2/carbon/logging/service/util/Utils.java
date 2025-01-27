/*
 * Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */

package org.wso2.carbon.logging.service.util;

import org.wso2.carbon.logging.service.LoggingConstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Utility class with methods used by logging service.
 */
public class Utils {

    /**
     * Util method to return the specified  property from a properties file.
     *
     * @param srcFile - The source file which needs to be looked up.
     * @param key     - Key of the property.
     * @return - Value of the property.
     */
    public static String getProperty(File srcFile, String key) throws IOException {

        String value;
        try (FileInputStream fis = new FileInputStream(srcFile)) {
            Properties properties = new Properties();
            properties.load(fis);
            value = properties.getProperty(key);
        } catch (IOException e) {
            throw new IOException("Error occurred while reading the input stream");
        }
        return value;
    }

    /**
     * Util method to return the list of keys for a given appender.
     *
     * @param srcFile      - The source file which needs to be looked up.
     * @param appenderName - Name of the appender.
     * @return - List of keys for the given appender.
     * @throws IOException - Error occurred while reading the input stream.
     */
    public static ArrayList<String> getKeysOfAppender(File srcFile, String appenderName) throws IOException {

        ArrayList<String> keys = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(srcFile)) {
            Properties properties = new Properties();
            properties.load(fis);
            for (String key : properties.stringPropertyNames()) {
                if (key.startsWith(LoggingConstants.APPENDER_PREFIX + appenderName)) {
                    keys.add(key);
                }
            }
        } catch (IOException e) {
            throw new IOException("Error occurred while reading the input stream");
        }
        return keys;
    }

    public static Map<String, String> getKeyValuesOfAppender(File srcFile, String appenderName) throws IOException {

        Map<String, String> keys = new HashMap<>();
        try (FileInputStream fis = new FileInputStream(srcFile)) {
            Properties properties = new Properties();
            properties.load(fis);
            for (String key : properties.stringPropertyNames()) {
                if (key.startsWith(LoggingConstants.APPENDER_PREFIX + appenderName)) {
                    keys.put(key, String.valueOf(properties.get(key)));
                }
            }
        } catch (IOException e) {
            throw new IOException("Error occurred while reading the input stream");
        }
        return keys;
    }
}
