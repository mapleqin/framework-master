…or create a new repository on the command line


echo # framework-master >> README.md
git init
git add README.md
git commit -m "first commit"
git remote add origin https://github.com/Toaker/framework-master.git
git push -u origin master
…or push an existing repository from the command line


git remote add origin https://github.com/Toaker/framework-master.git
git push -u origin master
…or import code from another repository

You can initialize this repository with code from a Subversion, Mercurial, or TFS project.


## License

If you use Toaker framework code in your application you should inform the author about it ( *email: ToakerQin[at]gmail[dot]com* ) like this:
> **Subject:** UIL usage notification<br />
> **Text:** I use Toaker framework {lib_version} in {application_name} - {http://link_to_google_play}.
> I [allow | don't allow] to mention my app in section "Applications using Toaker framework" on GitHub.

    Copyright 2011-2014 Sergey Tarasevich

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.