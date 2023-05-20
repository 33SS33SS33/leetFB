package aawork;

using System;
        using System.Collections.Generic;
        using System.IO;

public class test
{

    public const string encoding = "gbk";

    //JAVA TO C# CONVERTER WARNING: Method 'throws' clauses are not available in C#:
//ORIGINAL LINE: public static void main(String[] args) throws IOException
    public static void Main(string[] args)
    {
        File dir = new File("C:/Users/temp/Documents/test");
        List<File> allTxtFiles = new List<File>();
        getAlltxtFiles(dir, allTxtFiles);

        File destFile = new File("C:/Users/temp/Documents/all.txt");
        int num = 0;

        foreach (File srcFile in allTxtFiles)
        {
            num = num + 1;
            List<string> lines = new List<string>();
            readTxt(srcFile, lines);
            save(destFile, lines,num,srcFile.getPath());
        }
    }

    public static void readTxt(File file, List<string> lines)
    {
        FileStream fis = null;
        StreamReader sr = null;
        StreamReader br = null;
        try
        {
            fis = new FileStream(file, FileMode.Open, FileAccess.Read);
            sr = new StreamReader(fis, encoding);
            br = new StreamReader(sr);
            string line = null;
            while (!string.ReferenceEquals((line = br.ReadLine()), null))
            {
                lines.Add(line + Environment.NewLine);
            }
        }
        catch (FileNotFoundException e)
        {
            Console.WriteLine(e.ToString());
            Console.Write(e.StackTrace);
        }
        catch (IOException e)
        {
            Console.WriteLine(e.ToString());
            Console.Write(e.StackTrace);
        }
        finally
        {
            if (br != null)
            {
                try
                {
                    br.Close();
                }
                catch (IOException e)
                {
                    Console.WriteLine(e.ToString());
                    Console.Write(e.StackTrace);
                }
            }
            if (sr != null)
            {
                try
                {
                    sr.Close();
                }
                catch (IOException e)
                {
                    Console.WriteLine(e.ToString());
                    Console.Write(e.StackTrace);
                }
            }
            if (fis != null)
            {
                try
                {
                    fis.Close();
                }
                catch (IOException e)
                {
                    Console.WriteLine(e.ToString());
                    Console.Write(e.StackTrace);
                }
            }
        } // finally
    }

    /// <param name="line"> </param>
    public static void save(File file, List<string> lines, int index, string path)
    {
        FileStream fos = null;
        StreamWriter sw = null;
        StreamWriter bw = null;
        try
        {
            fos = new FileStream(file, true);
            sw = new StreamWriter(fos, encoding);
            bw = new StreamWriter(sw);
            bw.Write("ananpath" + path);
            bw.Write(Environment.NewLine);
            foreach (string line in lines)
            {
                bw.Write(line);
            }
        }
        catch (Exception e)
        {
            Console.WriteLine(e.ToString());
            Console.Write(e.StackTrace);
        }
        finally
        {
            if (bw != null)
            {
                try
                {
                    bw.Close();
                }
                catch (IOException e)
                {
                    Console.WriteLine(e.ToString());
                    Console.Write(e.StackTrace);
                }
            }
            if (sw != null)
            {
                try
                {
                    sw.Close();
                }
                catch (IOException e)
                {
                    Console.WriteLine(e.ToString());
                    Console.Write(e.StackTrace);
                }
            }
            if (fos != null)
            {
                try
                {
                    fos.Close();
                }
                catch (IOException e)
                {
                    Console.WriteLine(e.ToString());
                    Console.Write(e.StackTrace);
                }
            }
        } // finally
    }

    public static List<File> getAlltxtFiles(File dir, List<File> txtFiles)
    {
        File[] files = dir.listFiles();
        foreach (File file in files)
        {
            if (file.isDirectory())
            {
                getAlltxtFiles(file, txtFiles);
            }
            else if (file.getName().EndsWith(".py") || file.getName().EndsWith(".sh"))
            {
                txtFiles.Add(file);
                Console.WriteLine(file.getName());
            }
        }
        return txtFiles;
    }
}
