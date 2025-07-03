FROM node:lts-alpine

WORKDIR /app

COPY package*.json ./
COPY angular.json angular.json
COPY tsconfig.json tsconfig.json
COPY biome.json biome.json
COPY tsconfig.app.json tsconfig.app.json

RUN npm i

COPY src src

EXPOSE 4200

CMD npm start