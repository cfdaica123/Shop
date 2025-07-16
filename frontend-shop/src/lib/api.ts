import axios from "axios";

const instance = axios.create({
  baseURL: process.env.NEXT_PUBLIC_API_BASE_URL, // dùng biến môi trường
  withCredentials: true, // nếu có cookie thì true, không thì bỏ cũng được
});

export const fetchProducts = async () => {
  const res = await instance.get("/products");
  return res.data;
};

export default instance;
