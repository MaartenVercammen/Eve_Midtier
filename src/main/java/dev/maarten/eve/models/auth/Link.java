package dev.maarten.eve.models.auth;

import lombok.Builder;
import lombok.Setter;

import java.util.Map;

@Setter
@Builder
public class Link {

    private String schema;
    private String domain;
    private String path;
    private Map<String, String> parameters;

    public String getLink() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(schema);
        stringBuilder.append("://");
        stringBuilder.append(domain);
        stringBuilder.append(path);
        stringBuilder.append("?");
        parameters.forEach((key, value) -> stringBuilder.append(key).append("=").append(value).append("&"));
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }
}
