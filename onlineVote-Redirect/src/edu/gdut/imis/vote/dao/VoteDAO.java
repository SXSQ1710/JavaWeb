package edu.gdut.imis.vote.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import edu.gdut.imis.vote.model.VoteModel;

public class VoteDAO {
	public static void writeVoteModel(VoteModel vm,String fileName){
		
		try(ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(fileName)))) {
			out.writeObject(vm);
			out.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static VoteModel readVoteModel(String fileName){
		File file = new File(fileName);
		VoteModel vm = new VoteModel();
		if(!file.exists()){
			try {
				file.createNewFile();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			writeVoteModel(vm,fileName);
		}
		try (ObjectInputStream input = new ObjectInputStream(
							new BufferedInputStream(
									new FileInputStream(fileName)))){
			vm = (VoteModel) input.readObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vm;
	}
}
