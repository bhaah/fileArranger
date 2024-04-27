package project;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public abstract class MovingAction extends Action{
    protected List<File> downloads ;
    private int numberFilesToCheck ;

    public MovingAction(AllCourses courses) {
        super(courses);
        
        downloads = new ArrayList<>();
        initDownloads();
    }

    private void initDownloads(){
        File downloadsDirectory = new File(CourseFolderKeys.DOWNLOADS_DIRC_PATH);
        try {
            Files.list(downloadsDirectory.toPath()).limit(numberFilesToCheck).forEach(file->downloads.add(file.toFile()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    
}
