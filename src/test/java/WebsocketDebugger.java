import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.net.URI;
import java.net.URISyntaxException;

public class WebsocketDebugger extends JFrame implements ActionListener {

    private final JTextField uriField = new JTextField();
    private final JButton connectButton = new JButton("Connect");
    private final JButton closeButton = new JButton("Close");
    private final JTextArea responseTextArea = new JTextArea();
    private final JTextField requestTextField = new JTextField("");
    private WebSocketClient webSocketClient;

    public WebsocketDebugger(String defaultlocation) {
        super("WebSocket Client");
        Container c = getContentPane();
        GridLayout layout = new GridLayout();
        layout.setColumns(1);
        layout.setRows(6);
        c.setLayout(layout);

        uriField.setText(defaultlocation);
        c.add(uriField);

        connectButton.addActionListener(this);
        c.add(connectButton);

        closeButton.addActionListener(this);
        closeButton.setEnabled(false);
        c.add(closeButton);

        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(responseTextArea);
        c.add(scroll);

        requestTextField.addActionListener(this);
        c.add(requestTextField);

        java.awt.Dimension d = new java.awt.Dimension(300, 400);
        setPreferredSize(d);
        setSize(d);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (webSocketClient != null) {
                    webSocketClient.close();
                }
                dispose();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        String location;
        location = "ws://localhost:8090/ws_json";
        System.out.println("Default server url not specified: defaulting to \'" + location + "\'");
        new WebsocketDebugger(location);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == requestTextField) {
            if (webSocketClient != null) {
                webSocketClient.send(requestTextField.getText());
                requestTextField.setText("");
                requestTextField.requestFocus();
            }

        } else if (e.getSource() == connectButton) {
            try {
                webSocketClient = new MyWebSocketClient((new URI(uriField.getText())));

                closeButton.setEnabled(true);
                connectButton.setEnabled(false);
                uriField.setEditable(false);
                webSocketClient.connect();
            } catch (URISyntaxException ex) {
                responseTextArea.append(uriField.getText() + " is not a valid WebSocket URI\n");
            }
        } else if (e.getSource() == closeButton) {
            webSocketClient.close();
        }
    }

    class MyWebSocketClient extends WebSocketClient {

        public MyWebSocketClient(URI serverUri) {
            super(serverUri);
        }

        @Override
        public void onOpen(ServerHandshake handshake) {
            responseTextArea.append("You are connected to ChatServer: " + getURI() + "\n");
            responseTextArea.setCaretPosition(responseTextArea.getDocument().getLength());
        }

        @Override
        public void onMessage(String message) {
            responseTextArea.append("got: " + message + "\n");
            responseTextArea.setCaretPosition(responseTextArea.getDocument().getLength());
        }

        @Override
        public void onClose(int code, String reason, boolean remote) {
            responseTextArea.append(
                    "You have been disconnected from: " + getURI() + "; Code: " + code + " " + reason
                            + "\n");
            responseTextArea.setCaretPosition(responseTextArea.getDocument().getLength());
            connectButton.setEnabled(true);
            uriField.setEditable(true);
            closeButton.setEnabled(false);
        }

        @Override
        public void onError(Exception ex) {
            responseTextArea.append("Exception occurred ...\n" + ex + "\n");
            responseTextArea.setCaretPosition(responseTextArea.getDocument().getLength());
            ex.printStackTrace();
            connectButton.setEnabled(true);
            uriField.setEditable(true);
            closeButton.setEnabled(false);
        }
    }
}