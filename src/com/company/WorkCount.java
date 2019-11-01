package com.company;


import java.io.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class WorkCount {

    public static void main(String[] args) throws IOException {

        int maxValue = 0;
        String name = null;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите путь к файлу: ");
        System.out.println("Можете ввести абсолютный путь к файлу.");
        System.out.println("А также можете ввести название файла находящегося в папке проекта.");
        name = bufferedReader.readLine();
        if (name.isEmpty()) {
            System.err.println("Вы ничего не ввели");
            bufferedReader.close();
        } else {
            try {

                Path testFilePath = Paths.get(name);
                File textFile = new File(String.valueOf(testFilePath.toAbsolutePath()));
                BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(textFile), "windows-1251"));


                TreeMap<String, Integer> Map = new TreeMap();
                String line = null;


                while ((line = input.readLine()) != null) {

                    line= line.replaceAll("[^a-zA-Zа-яёА-ЯЁ]", " ");
                    StringTokenizer parser = new StringTokenizer(line);
                    while (parser.hasMoreTokens()) {
                        String currentWord = parser.nextToken().toLowerCase();
                        Integer count = Map.get(currentWord);
                        if (count == null) {
                            count = 0;
                        }

                        Map.put(currentWord, ++count );
                    }

                }

                for (String key : Map.keySet()) {
                    System.out.print(key + " ");
                }
                System.out.println(" ");
                System.out.println(Map);
                maxValue = Collections.max(Map.values());

                System.out.println("Больше раз встречаются (частота " + maxValue + ") следующие слова: ");
                for (Map.Entry<String, Integer> entry : Map.entrySet()) {
                    if (entry.getValue().equals(maxValue)) {
                        System.out.println(entry.getKey());
                    }
                }

            }

            catch (FileNotFoundException e){
                System.err.println("Файл не найден!");
            }

            catch (IOException ie) {
                ie.printStackTrace();
            }
            finally {
                bufferedReader.close();
            }}}}