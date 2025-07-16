"use client";

import { useEffect, useState } from "react";
import { fetchProducts } from "@fe/lib/api";

type Product = {
  id: number;
  name: string;
  description: string;
  price: number;
  stockQuantity: number;
  scale: string;
  dimensions: string;
  createdAt: string;
};

export default function ProductList() {
  const [products, setProducts] = useState<Product[]>([]);

  useEffect(() => {
    const load = async () => {
      try {
        const data = await fetchProducts();
        setProducts(data);
      } catch (err) {
        console.error("Failed to fetch:", err);
      }
    };
    load();
  }, []);

  console.log("BASE_URL:", process.env.NEXT_PUBLIC_API_BASE_URL);


  return (
    <div className="p-4">
      <h1 className="text-xl font-bold mb-4">Danh sách sản phẩm</h1>
      <ul className="space-y-2">
        {products.map((product) => (
          <li key={product.id} className="border p-4 rounded shadow-sm">
            <h2 className="text-lg font-semibold">{product.name}</h2>
            <p>{product.description}</p>
            <p className="text-sm text-gray-500">
              Giá: {product.price.toLocaleString()}₫
            </p>
            <p className="text-sm">Tồn kho: {product.stockQuantity}</p>
            <p className="text-sm">Tỉ lệ: {product.scale}</p>
            <p className="text-sm">Kích thước: {product.dimensions}</p>
          </li>
        ))}
      </ul>
    </div>
  );
}
