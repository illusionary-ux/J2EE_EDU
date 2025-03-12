package com.example.demo.view;

import com.example.demo.entity.User;

public class UserRender {
    String html = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>User Information</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "    <h1>User Information</h1>\n" +
            "    <table border=\"1\">\n" +
            "        <tr>\n" +
            "            <th>Key</th>\n" +
            "            <th>Value</th>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <td>Photo</td>\n" +
            "            <td><img src=\"/%s\" alt=\"User Photo\"></td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <td>UID</td>\n" +
            "            <td>%s</td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <td>Name</td>\n" +
            "            <td>%s</td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <td>Age</td>\n" +
            "            <td>%d</td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <td>Gender</td>\n" +
            "            <td>%s</td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <td>Introduction</td>\n" +
            "            <td>%s</td>\n" +
            "        </tr>\n" +
            "    </table>\n" +
            "</body>\n" +
            "</html>";

    public String userPage(User user) {
        String html_final = String.format(html,
                user.getPhoto(),
                user.getUID(),
                user.getName(),
                user.getAge(),
                user.getGender().toString(),
                user.getIntro());
        return html_final;
    }
}
