package project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseFolderKeys {
    private static final Map<List<String>, String> folderKeys = new HashMap<>() {{
        put(List.of( "lecture", "summary", "lec"), "lects");
        put(List.of("homework", "work","hw"), "homeWorks");
        put(List.of("practice","exercise"),"exercise");
    }};
    public static final String REST_FILES = "restFiles";
    public static final String RELATED_NAMES_FILE_NAME="relatedNames.txt";
    public static final String NOTES_FILE_NAME = "notes.txt";
    public static final String DIRECTORY_PATH="../Courses";
    public static final String DOWNLOADS_DIRC_PATH = "../Downloads";
    public static String getRelatedFolder(String fileName){
        for (List<String> keysList : folderKeys.keySet()) {
            for (String key : keysList) {
                if(fileName.contains(key)) return folderKeys.get(keysList);
            }
        }
        return REST_FILES;
    }
    public static String courseDirc(String courseName) {
        return "../Courses/"+courseName;
    }
}
