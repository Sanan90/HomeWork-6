import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static Socket clientSocket;
    private static ServerSocket server;
    private static BufferedReader in;
    private static BufferedWriter out;
    private static BufferedReader reader;

    public static void main(String[] args) {

        try {
            try {

                server = new ServerSocket(4325);
                System.out.println("Сервер готов");
                clientSocket = server.accept();
                System.out.println("соединение с клиентом");

                try {
                    while (true) {
                        reader = new BufferedReader(new InputStreamReader(System.in));
                        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                        if (in.ready()) {
                            String clientWord = in.readLine();
                            System.out.println(clientWord);
                            out.write("");
                            out.flush();
                        }

                        if (reader.ready()) {
                            String serverword = reader.readLine();
                            out.write(serverword + "\n");
                            out.flush();
                        }


                    }
                } finally {
                    clientSocket.close();
                    in.close();
                    out.close();
                }

            } finally {
                server.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}