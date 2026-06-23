FROM mcr.microsoft.com/devcontainers/base:ubuntu-24.04

# Install the Clojure CLI. The Java runtime it needs is provided by the
# devcontainer Java feature.
RUN apt-get update \
    && export DEBIAN_FRONTEND=noninteractive \
    && apt-get -y install --no-install-recommends rlwrap \
    && rm -rf /var/lib/apt/lists/* \
    && curl -fsSL -o /tmp/linux-install.sh https://download.clojure.org/install/linux-install.sh \
    && chmod +x /tmp/linux-install.sh \
    && /tmp/linux-install.sh \
    && rm /tmp/linux-install.sh
