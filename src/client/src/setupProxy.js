const { createProxyMiddleware } = require('http-proxy-middleware')

module.exports = app => {
  app.use(
    '/api',
    createProxyMiddleware({
      target: 'http://app:8080',
      pathRewrite: {
        '^/api/': '/',
      },
    })
  )
}
