# employee-service
Employee service
Two apis exposed using Apache Camel DSL
1. POST - /api/employee/authorize
  Provides access token
  payload:
  {
    "message digest encrypted with PRIVATE KEY......"
  }
  output:
  {
    "access token encrypted with secret key"
  }
2. POST - /api/employee/register
  header:
    accessToken - use generated accesstoken from first api.
  payload:
  {
    "firstName":"Santosh",
    "lastName":"BS",
    "gender":"M",
    "email":"pavanraju.mca@gmail.com",
    "mobile":"9951781272"
  }
  Output:
  {
    "id":"xxxxxxx",
    "status":"Already exist/Created"
  }
