CLASSPATH = lib/*:.

TryCommonMark.class: TryCommonMark.java
	javac -g -cp $(CLASSPATH) TryCommonMark.java

MarkdownParse.class: MarkdownParse.java
	javac -cp $(CLASSPATH) MarkdownParse.java

MarkdownParseTest.class: MarkdownParse.class MarkdownParseTest.java
	javac -cp $(CLASSPATH) MarkdownParseTest.java

test: MarkdownParseTest.class
	java -cp $(CLASSPATH) org.junit.runner.JUnitCore MarkdownParseTest

