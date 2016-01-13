javadoc -d docs/ $(find -name *.java)


git fetch --depth=1 javadoc gh-pages
git add --all
git commit -m "Add Documentation"
git merge --no-edit -s ours remotes/javadoc/gh-pages
git push javadoc master:gh-pages
