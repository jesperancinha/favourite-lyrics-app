#!/bin/bash
echo "Make sure you've started with . ./node16.sh"
export NVM_DIR="$HOME/.nvm"
[ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh" c
nvm install 16.13.0
nvm use 16.13.0
