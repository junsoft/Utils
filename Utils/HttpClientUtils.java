

import org.apache.commons.io.IOUtils;
import sun.net.www.protocol.http.HttpURLConnection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by jun on 16/1/22.
 */
public class HttpClientUtils {
    public static final byte[] getTSAData(String url,byte[] token) throws IOException {
        URL realUrl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setUseCaches(false);
        conn.setRequestProperty("Content-Type", "application/timestamp-query");
        conn.setRequestProperty("Content-Transfer-Encoding", "binary");
        conn.setRequestMethod("POST");
        conn.connect();
        OutputStream os = conn.getOutputStream();
        os.write(token);
        os.flush();
        os.close();
        InputStream is = conn.getInputStream();
        byte[] data = IOUtils.toByteArray(is);
        is.close();
        return data;
    }

    public  static final byte[] sendPost(String url, String param) throws IOException {
        PrintWriter out = null;
        InputStream input = null;
        URL realUrl = new URL(url);
        URLConnection conn = realUrl.openConnection();
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("user-agent",
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
        conn.setRequestProperty("ContentType ", "application/pdf;charset=utf-8");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        out = new PrintWriter(conn.getOutputStream());
        out.print(param);
        out.flush();
        out.close();
        input = conn.getInputStream();
        byte[] data = IOUtils.toByteArray(input);
        input.close();
        return data;
    }
}
