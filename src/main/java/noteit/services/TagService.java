package noteit.services;

import noteit.blog.Tag;

public class TagService extends GenericCRUD<Tag> {
    private static TagService instance;

    private TagService(){ super(Tag.class); }

    public static TagService getInstance() {
        if (instance == null) {
            instance = new TagService();
        }
        return instance;
    }
}
