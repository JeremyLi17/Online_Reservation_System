package utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author jeremy on 2022/12/6
 */
public class ParameterStringBuilder {
    public static String getParamsString(Map<String, String> params)
            throws UnsupportedEncodingException {
        StringBuilder res = new StringBuilder();
        res.append("?");

        for (Map.Entry<String, String> entry: params.entrySet()) {
            res.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            res.append("=");
            res.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            res.append("&");
        }

        String result = res.toString();
        return result.length() > 0 ? result.substring(0, res.length() - 1) : result;
    }
}
