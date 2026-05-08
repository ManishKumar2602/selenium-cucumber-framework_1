Here's the quickest and most common method that most developers use:

Step 1: Create GitHub Repository
Go to github.com
Click "+" → "New repository"
Name: selenium-cucumber-framework_1
Click "Create repository"
Copy the URL (shown after creation)
Step 2: Open PowerShell in Your Project
cd "c:\Desktop\JOB SKILLS\Selenium_Cumcumber\selenium-cucumber-framework_1"

git init
git add .
git commit -m "Initial commit"
git branch -M main
git remote add origin https://github.com/YOUR_USERNAME/selenium-cucumber-framework_1.git
git push -u origin main

When prompted for password, enter your GitHub Personal Access Token (PAT)

That's It! 🎉
Your project is now on GitHub!

What Each Command Does:
Command	What it does
git init	Initialize git (start tracking)
git add .	Add all files (except .gitignore)
git commit -m "message"	Save changes with a message
git branch -M main	Rename branch to main
git remote add origin [URL]	Connect to GitHub
git push -u origin main	Upload to GitHub
