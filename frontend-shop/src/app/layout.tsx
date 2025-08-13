import React from "react";
import { Metadata } from "next";

import Header from "@fe/components/layout/Header";
import Footer from "@fe/components/layout/Footer";
import "./globals.css";

export const metadata: Metadata = {
  title: {
    template: "%s | Kekikofee",
    default: "Kekikofee - Simple Joys, Daily",
  },
  description: "Tiệm bánh và cà phê mang lại những niềm vui giản đơn mỗi ngày.",

  icons: {
    icon: "/images/Kekikofee-favicon.png",
  },
};

export default function RootLayout({
  children,
}: {
  readonly children: React.ReactNode;
}) {
  return (
    <html lang="vi">
      <body>
        <Header />
        <main>{children}</main>
        <Footer />
      </body>
    </html>
  );
}
