import com.petproject.projectSpring.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class OtherController {

    @Autowired
    private PostRepository postRepository;
}
