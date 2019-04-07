import java.io.*;
import java.util.*;

class MainGUI
{
	public static void main(String [] args) throws Exception
	{
		int opt,vopt,tmp1,tmp3;
		String tmp2;
		String fname,str;
		String key=new String("Y");

		FindIt objF = new FindIt();
		AddIt objA = new AddIt();

		Scanner sc = new Scanner(System.in);

		do
		{
			System.out.print("\n1.Enter Data to the Dictionary\n2.Search for the word by entering Chracters\nOption :  ");
			opt = sc.nextInt();
			switch(opt)
			{
				case 1:
					System.out.print("\n1.Select a file (By entering name)\n2.Enter a word directly\nOption : ");
					vopt = sc.nextInt();
					switch(vopt)
					{
						case 1:
							System.out.print("Enter the name of the file : ");
							fname = sc.next();
							File f = new File(fname);
							objA.fAcceptor(f);
							break;

						case 2:
							System.out.print("Enter the word : ");
							str = sc.next();
							objA.wAcceptor(str);
							break;
					}

				break;


				case 2:
					System.out.print("Now Fella, Enter the number of characters : ");
					tmp1 = sc.nextInt();
					String []name = new String [tmp1];

					for(int i=0;i<tmp1;i++)
					{
						System.out.print("Enter the character : ");
						tmp2 = sc.next();
						name[i] = tmp2;
					}
					//find(name);


				break;
			}
			System.out.print("Do you want to continue ?(Y or N)\nChoice : ");
			key=sc.next();
		}while(key.compareTo("y")==0||key.compareTo("Y")==0);

	}
}

class FindIt
{
	void find(String str)
	{
		int len = str.length();
		//step 1 : find number of characters
		//send the word(i.e. set of char) to the search function and send name of File as well
	}

	//void search(String,File);

}



class AddIt
{
	void fAcceptor(File f) throws Exception//takes name of the file and add data to dictionary
	{
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		String str = new String("String");

		if(f.exists()==false)
		{
			System.out.println("File Not Found");
			return;
		}

		while(str!=null)
		{

			try
			{
				str = br.readLine();
			}
			catch(Exception e)
			{
				System.out.println(e);
				break;
			}
			if(str==null)
				break;
			//System.out.println(str);
			StringTokenizer st = new StringTokenizer(str);
			while(st.hasMoreTokens())
			{
				String ak = new String(st.nextToken());
				int slen = ak.length();
				if(ak.charAt(slen-1)=='.' || ak.charAt(slen-1)==',' || ak.charAt(slen-1)==';' || ak.charAt(slen-1)=='?' || ak.charAt(slen-1)=='"' || ak.charAt(slen-1)=='!')
				{
					StringBuilder sb = new StringBuilder(ak);
					sb.deleteCharAt(slen-1);
					ak = sb.toString();
				}
				if(ak.charAt(0)=='"' || ak.charAt(0)=='(')
				{
					StringBuilder sb = new StringBuilder(ak);
					sb.deleteCharAt(0);
					ak = sb.toString();	
				}
				wAcceptor(ak);
			}
		}

	}

	void wAcceptor(String str) throws Exception
	{
		int len = str.length();
		FileOutputStream fos = new FileOutputStream("F"+len+".txt",true);
		wordAdder(str,fos);
	}

	void wordAdder(String str,FileOutputStream fos) throws Exception//function to be used by both above functions to add word to the dictionary
	{
		DataOutputStream dos = new DataOutputStream(fos);
		String gh = new String(str);
		dos.writeBytes(gh+" \n");
		
		dos.close();
	}
}