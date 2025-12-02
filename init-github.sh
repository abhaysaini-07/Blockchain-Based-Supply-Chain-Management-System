#!/bin/bash

# Script to initialize GitHub repository for Blockchain Supply Chain Management System

echo "Initializing GitHub repository..."

# Check if git is initialized
if [ ! -d ".git" ]; then
    echo "Initializing git repository..."
    git init
fi

# Add all files
echo "Adding files to git..."
git add .

# Create initial commit
echo "Creating initial commit..."
git commit -m "Initial commit: Blockchain-Based Supply Chain Management System

Features implemented:
- OOP (Polymorphism, Inheritance, Exception Handling, Interfaces)
- Collections & Generics
- Multithreading & Synchronization
- Database Operations (JDBC)
- Servlets & Web Integration
- Complete blockchain implementation for supply chain tracking"

echo ""
echo "Repository initialized successfully!"
echo ""
echo "Next steps:"
echo "1. Create a new repository on GitHub"
echo "2. Add the remote: git remote add origin <your-repo-url>"
echo "3. Push the code: git push -u origin main"
echo ""
echo "Or if using master branch:"
echo "3. Push the code: git push -u origin master"

