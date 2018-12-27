package demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import util.StaticUtilTool;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class Sample {
    static StaticUtilTool staticUtilTool = new StaticUtilTool();
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) throws Exception {

        deal_openjdk_files();
    }

    private static void deal_openjdk_files() throws IOException {
        String root_file_str = "C:" + File.separator + "WebstormProjects" + File.separator + "dir_md"
                + File.separator + "openjdk_download_and_introduce" + File.separator + "files" + File.separator + "_7z";

        LinkedList<String> linkedList_justName = staticUtilTool.get_path_list_by_root(root_file_str, true);
        LinkedList<String> linkedList_notName = staticUtilTool.get_path_list_by_root(root_file_str, false);
        String replace_str = "https://github.com/littleostar-toolbox/openjdk_download_and_introduce/raw/master/files/_7z/";

        for (int i = 0; i < linkedList_notName.size(); i++) {
            String element = linkedList_notName.get(i).replace(root_file_str + File.separator + "", replace_str);
            linkedList_notName.set(i, element);
        }

//        printJson(linkedList_justName);
        get_md_content(linkedList_justName, linkedList_notName);

    }

    private static void get_md_content(LinkedList<String> linkedList_justName, LinkedList<String> linkedList_notName) {
        String str_justname_first = linkedList_justName.get(0);
        String str_justname_file_name = get_file_name_by_ele(str_justname_first);
//        System.out.println("  - " + str_justname_file_name);

        int count = 0;
        boolean morePrintLn = false;

        for (int i = 0; i < linkedList_justName.size(); i++) {
            int index = i;
            String str_justname = linkedList_justName.get(index);
            String str_notname = linkedList_notName.get(index);
            if (str_justname.contains(str_justname_file_name)) {
                if (count == 0) {
                    System.out.println("---\n  - " + str_justname_file_name);
                }
                count = count + 1;
                morePrintLn = true;
            } else {
                str_justname_file_name = get_file_name_by_ele(linkedList_justName.get(index));
                System.out.println("---\n  - " + str_justname_file_name);
                count = 1;
            }
            System.out.println("    - " + "[" + str_justname + "]" + "(" + str_notname + ")");
            System.out.print(morePrintLn ? "\n": "");
        }
    }

    private static String get_file_name_by_ele(String str_justname_ele) {
        return str_justname_ele.substring(0, str_justname_ele.indexOf(".7z"));
    }

    private static void printJson(LinkedList<String> linkedList) {
        String json = gson.toJson(linkedList, linkedList.getClass());
        System.out.println(linkedList.size());
        System.out.println(json);
    }
}
