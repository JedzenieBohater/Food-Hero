FROM node:10-slim

WORKDIR /app

ENV PATH /app/node_modules/.bin:$PATH

COPY package.json .

RUN npm install --silent

COPY . .

CMD ["npm", "start"]