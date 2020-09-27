package model.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import model.Client;
import model.EntityKey;
import model.structures.HashTable;

public class LoadClientsFromFile {
	
	public static final String RANDOM_NAMES_PATH = "data/randoms/names.txt";
	public static final String RANDOM_SURNAMES_PATH = "data/randoms/surnames.txt";

	public static void loadUsersTo(HashTable<EntityKey, Client> clients) throws IOException {
		Random idGenerator = new Random();
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<String> surnames = new ArrayList<String>();
		
		BufferedReader br = new BufferedReader(new FileReader(RANDOM_NAMES_PATH));
		String line = br.readLine();
		while(line != null) {
			names.add(line);
			line = br.readLine();
		}
		br.close();
		
		br = new BufferedReader(new FileReader(RANDOM_SURNAMES_PATH));
		line = br.readLine();
		while(line != null) {
			surnames.add(line);
			line = br.readLine();
		}
		br.close();
		
		for(int i = 0; i < 100; i++) { //Both files have 100 lines. So, 100 clients.
			String name = names.get((int) (Math.random()*100)) + " " + surnames.get((int) (Math.random()*100));
			String id = String.valueOf(idGenerator.nextInt());
			int priority = (int)(Math.random()*3);
			Client client = new Client(name, id, LocalDate.now(), priority);
			clients.add(client.getUserKey(), client);
		}
	}
}
