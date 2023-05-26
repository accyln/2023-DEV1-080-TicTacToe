FROM node:17-alpine

WORKDIR /usr/src/app

# add `/usr/src/app/node_modules/.bin` to $PATH
ENV PATH /usr/src/app/node_modules/.bin:$PATH

# install and cache app dependencies
COPY src/webui/public /usr/src/app/public
COPY src/webui/src /usr/src/app/src
COPY src/webui/package.json /usr/src/app/package.json
RUN npm install

EXPOSE 3000

CMD ["npm", "start"]