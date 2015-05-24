package schism;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class schism {
	public static void main(String[] args) throws Exception{
		System.out.println("process starting: "+System.currentTimeMillis());
		FileWriter fw = new FileWriter("test.txt");
		//假设现在数据库里有100个节点
		int number_triple=300000;
		Random random = new Random(System.currentTimeMillis());
		int number_edge = 0,total_edge=0;
		int weight = 0;
		int id = 0;
		///String line="";
		String[] text = new String[number_triple+1];
		for(int i=0;i<number_triple+1;i++){
			text[i]="";
		}
		fw.write(number_triple+" "+total_edge+" 001\n");
		for(int i=1;i<number_triple+1;i++){
			number_edge = random.nextInt(10)+1;
			//total_edge+=number_edge;
			for(int j=0;j<number_edge;j++){
				id = random.nextInt(number_triple)+1;
				weight = random.nextInt(5)+1;
				if(id>i){
					total_edge++;
					text[i] = text[i]+id+" "+weight+" ";
					//System.out.println(text[i]);
					text[id] = text[id]+(i+1)+" "+weight+" ";

					//System.out.println(id+" "+text[id]);
				}
			}
			fw.write(text[i]+"\n");
			text[i]="";
			
		}
		System.out.println(total_edge);
		//fw.write(line);
		System.out.println(System.currentTimeMillis());
		fw.flush();
		fw.close();
	}

}
