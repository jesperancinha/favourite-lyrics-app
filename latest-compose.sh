#!/bin/bash
#Source: https://gist.github.com/deviantony/2b5078fe1675a5fedabf1de3d1f2652a
COMPOSE_VERSION=$(curl -s https://api.github.com/repos/docker/compose/releases/latest | grep 'tag_name' | cut -d\" -f4)
mkdir -p /usr/local/lib/docker/cli-plugins
sh -c "curl -L https://github.com/docker/compose/releases/download/${COMPOSE_VERSION}/docker-compose-`uname -s`-`uname -m` > /usr/local/lib/docker/cli-plugins/docker-compose"
chmod +x /usr/local/lib/docker/cli-plugins/docker-compose
docker compose version
exit 0