package com.gmail.seizans.curl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public final class Curl {

	public static void main(String[] args) {
		String host = "www.google.co.jp";
		int port = 80;
		String path = "/";
		Socket socket = null;
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			socket = new Socket(host, port);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			writer.write("GET " + path + " HTTP/1.0\r\n");
			writer.write("Host: " + host + ":" + port + "\r\n");
			writer.write("\r\n");
			writer.flush();

			while (true) {
				String line = reader.readLine();
				if (line == null) {
					break;
				}
				System.out.println(line);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
