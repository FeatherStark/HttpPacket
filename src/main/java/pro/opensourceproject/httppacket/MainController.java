package pro.opensourceproject.httppacket;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import org.apache.commons.lang.StringEscapeUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class MainController {

    @FXML
    private Button GetCopyButton;

    @FXML
    private TextArea GetResponeData;

    @FXML
    private Button GetLastHistoryButton;

    @FXML
    private TabPane MainTablePanne;

    @FXML
    private Button GetRequireDataButton;

    @FXML
    private TextArea GetRequireData;

    @FXML
    private AnchorPane MainGETBar;

    @FXML
    private Button GetNextHistoryButton;

    @FXML
    private Button ClickResponseCpoyButton;

    @FXML   //复制 请求 按钮，复制请求内容到剪切板
    void ClickButtonCopy(ActionEvent event) {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();

        content.putString(GetRequireData.getText());  //复制请求内容到剪切板
        clipboard.setContent(content);
    }

    @FXML
    void ClickGetRequireDataButton(ActionEvent event) {
        System.out.println("ClickGetRequireDataButton");
        String RequireContent = GetRequireData.getText();
        String[] strs = RequireContent.split("\n");
        String[] URIs = strs[0].split(" "); //获取首行中的第二个 uri
//        System.out.println(StringEscapeUtils.unescapeJava(strs[1]));

        try {
            String TargetUrl = "";
            for(int i = 1;i<strs.length;i++){
                String[] Headers = strs[i].split(": ");
                if(Headers[0].equals("Host")){
                    TargetUrl = "http://"+Headers[1]+URIs[1];
                }
            }
            URL url = new URL(TargetUrl);
            URLConnection connection = url.openConnection();
            for(int i = 1;i<strs.length;i++){
                String[] Headers = strs[i].split(": ");
                connection.setRequestProperty(Headers[0],Headers[1]);
            }
            try (InputStream in = connection.getInputStream()){
                InputStream buffer = new BufferedInputStream(in);
                Reader reader = new InputStreamReader(buffer);
                int c;
                String HtmlBody = "";
                while ((c = reader.read())!= -1) {
//                    System.out.print((char)c);
//                    System.out.println("===========");
                    HtmlBody += (char)c;
                    System.out.print((char)c);
                }
                GetResponeData.setText(HtmlBody);
            } catch (MalformedURLException e) {
              }
        } catch (IOException e) {
        }
    }

    @FXML
    void ClickButtonResponseCpoy(ActionEvent event) {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();

        content.putString(GetResponeData.getText());  //复制请求内容到剪切板
        clipboard.setContent(content);
    }
}
