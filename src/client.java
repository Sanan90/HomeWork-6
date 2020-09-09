import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class client {
    private static Socket clientSocket; //сокет для общения
    private static BufferedReader reader; // нам нужен ридер читающий с консоли, иначе как
    // мы узнаем что хочет сказать клиент?
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет

    public static void main(String[] args) {

        try {
            try {

                    clientSocket = new Socket("", 4325);
                    while (true) {

                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                        reader = new BufferedReader(new InputStreamReader(System.in));

                        if (in.ready()) {
                            String serverWord = in.readLine();
                            System.out.println(serverWord);
                        }

                        if (reader.ready()) {
                            String clientWord = reader.readLine();
                            out.write(clientWord + "\n");
                            out.flush();
                        }
                    }
        } finally {
            clientSocket.close();
            in.close();
            out.close();
        }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
