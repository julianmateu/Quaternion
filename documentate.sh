#!/bin/bash

# A script to generate documentation in the ./docs/ directory using javadocs
# and upload it to a GitHub repo, in a branch called gh-pages. To create it
# run the following commands once in the ./docs/ directory (uncomment the
# lines that start with "#", and change the data between <,>.

if [ "$1" == "" ]; then
    javadoc -d docs/ $(find -name *.java)

    #cd docs
    #git init
    #git remote add javadoc https://<your name>@github.com/<your name>/<your project>.git

    cd docs
    git fetch --depth=1 javadoc gh-pages
    git add --all
    git commit -m "Add Documentation"
    git merge --no-edit -s ours remotes/javadoc/gh-pages
    git push javadoc master:gh-pages
fi
