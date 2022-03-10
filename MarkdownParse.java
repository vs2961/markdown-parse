// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import org.commonmark.node.*;
import org.commonmark.parser.Parser;

public class MarkdownParse {

    public static ArrayList<String> getLinks(String contents) {
        Parser parser = Parser.builder().build();
        Node node = parser.parse(contents);

        WordCountVisitor visitor = new WordCountVisitor();
        node.accept(visitor);

        return visitor.res;
    }
    public static void main(String[] args) throws IOException {
        

		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);

        System.out.println(getLinks(contents));
    }
}

class WordCountVisitor extends AbstractVisitor {
    int wordCount = 0;
    ArrayList<String> res = new ArrayList<>();

    @Override
        public void visit(Link link) {
            // This is called for all Text nodes. Override other visit methods for other node types.

            // Count words (this is just an example, don't actually do it this way for various reasons).
            res.add(link.getDestination());
            wordCount++;
            //res.add();

            // Descend into children (could be omitted in this case because Text nodes don't have children).
            visitChildren(link);
        }
}

