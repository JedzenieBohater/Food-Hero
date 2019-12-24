FROM node:lts-alpine AS builder

WORKDIR /usr/src/app

ENV NPM_CONFIG_LOGLEVEL warn

ENV PATH /usr/src/app/node_modules/.bin:$PATH

COPY package.json .

RUN npm install --no-optional --silent && npm cache clean --force

COPY . .

RUN npm run build

FROM nginx:1.13.12-alpine

COPY --from=builder /usr/src/app/build /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]