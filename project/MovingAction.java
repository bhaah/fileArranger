package project;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class MovingAction extends Action{
    protected List<File> downloads ;
    private int numberFilesToCheck=10 ;

    public MovingAction(AllCourses courses) {
        super(courses);
        initDownloads();
    }

    private void initDownloads(){
        downloads = new ArrayList<>();
        File[] allFiles = getDownloadsArrayFromDirc();
        setLastFilesInDownloadsList(allFiles);  
    }

    private File[] getDownloadsArrayFromDirc(){
        return new File(CourseFolderKeys.DOWNLOADS_DIRC_PATH).listFiles();
    }
    
    private void setLastFilesInDownloadsList(File[] allFiles){
        int startIndex = Math.max(0, allFiles.length-numberFilesToCheck) ;
        for (int index = startIndex;index<allFiles.length;index++) {
            downloads.add(allFiles[index]);
        }
    }
}

