
for file in test-files/*.md;
do
  java -cp lib/*:. MarkdownParse $file; echo $file
done
