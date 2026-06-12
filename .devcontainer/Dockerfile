FROM mcr.microsoft.com/devcontainers/base:2.0.4-noble

# Install the Clojure CLI (deps.edn tooling). The JDK is provided by the
# java devcontainer feature and is only needed at runtime, so installing the
# CLI here at image-build time is sufficient. rlwrap powers the `clj` REPL.
RUN apt-get update && export DEBIAN_FRONTEND=noninteractive \
    && apt-get -y install --no-install-recommends curl rlwrap \
    && rm -rf /var/lib/apt/lists/* \
    && curl -fsSL -o /tmp/linux-install.sh https://github.com/clojure/brew-install/releases/latest/download/linux-install.sh \
    && chmod +x /tmp/linux-install.sh \
    && /tmp/linux-install.sh \
    && rm -f /tmp/linux-install.sh
