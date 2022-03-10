import java.util.ArrayList;

import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

class TryCommonMark {
    public static void main(String[] args) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse("This is *Sparta*");
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        System.out.println(renderer.render(document));  // "<p>This is <em>Sparta</em></p>\n"

        // this part actually does the computation
        Node node = parser.parse("[a link!](https://something.com)\n[another link!](some-page.html)");
        WordCountVisitor visitor = new WordCountVisitor();
        node.accept(visitor);
        System.out.println(visitor.wordCount);  // 4
        System.out.println(visitor.res);

    }
}


//this class can be defined anywhere in the file
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
