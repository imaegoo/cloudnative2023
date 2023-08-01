import axios from "axios";
import { serverUrl } from "./constants";

export async function checkLoginStatus() {
  try {
    const res = await axios.request({
      baseURL: serverUrl,
      url: '/api/user/me',
      withCredentials: true,
    });
    if (res?.data?.data) {
      return true;
    }
  } catch (e) {}

  return false;
}
