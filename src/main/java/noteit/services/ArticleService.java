package noteit.services;

import noteit.blog.Article;

public class ArticleService extends GenericCRUD<Article> {
    private static ArticleService instance;

    private ArticleService(){ super(Article.class); }

    public static ArticleService getInstance() {
        if (instance == null) {
            instance = new ArticleService();
        }
        return instance;
    }
}
