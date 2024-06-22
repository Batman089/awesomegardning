export class ApiRoutes {
  private static API = '/api';
  static BACKEND = 'http://localhost:8080/api';
  static BACKEND_PLANTS = `${ApiRoutes.BACKEND}/plants`;
  static PLANTS = `${ApiRoutes.API}/plants`;
  static PLANTSDETAILS = `${ApiRoutes.PLANTS}/details`;
}
