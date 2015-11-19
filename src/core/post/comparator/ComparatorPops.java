package core.post.comparator;

import java.util.Comparator;
import core.post.Post;

public class ComparatorPops implements Comparator<Post> {

	@Override
	public int compare(Post post1, Post post2) {
		if (post1.getPopularidade() > post2.getPopularidade()) {
			return 1;
		}
		if (post1.getPopularidade() < post2.getPopularidade()) {
			return -1;
		}
		return 0;
	}

}
