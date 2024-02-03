"use client";
import React, { useState } from "react";
import { NextUIProvider } from "@nextui-org/react";
import { Input } from "@nextui-org/react";
import { SearchIcon } from "./SearchIcon";
import { Button } from "@nextui-org/react";
import CustomModal from "./Modal";

export default function Home() {
  const [isOpen, setIsOpen] = useState(false);
  const [size, setSize] = useState("md");
  const [inputText, setInputText] = useState("");
  const [responseText, setResponseText] = useState("");

  const handleOpenModal = (size) => {
    setSize(size);
    setIsOpen(true);
  };

  const handleCloseModal = () => {
    setIsOpen(false);
  };

  const handleKeyDown = (e) => {
    if (e.key === "Enter") {
      e.preventDefault();
      handleCheckPalindrome();
    }
  };

  const handleCheckPalindrome = async () => {
    if (!inputText.trim()) {
      setResponseText("Input can't be empy, Kindly insert a word");
      handleOpenModal("md");
      return;
    }

    try {
      const response = await fetch(
        "http://localhost:8081/api/methods/ispalindrome",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/x-www-form-urlencoded",
          },
          body: new URLSearchParams({ string: inputText }),
        }
      );

      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }

      const responseData = await response.json();

      setResponseText(
        responseData
          ? "The text is a palindrome"
          : "The text is not a palindrome"
      );
      handleOpenModal("md");
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };
  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24">
      <NextUIProvider>
        <h1
          style={{
            fontFamily: "cursive",
            fontSize: "44px",
            textAlign: "center",
          }}
        >
          SCHOOLSOFT
        </h1>
        <h2
          style={{
            fontFamily: "cursive",
            fontSize: "34px",
            textAlign: "center",
          }}
        >
          Palindrome Tester
        </h2>
        <div className="w-[780px] h-[240px] px-8 rounded-2xl flex justify-center items-center bg-gradient-to-tr from-pink-500 to-yellow-500 text-white shadow-lg">
          <Input
            onKeyDown={handleKeyDown}
            label="Test"
            isClearable
            radius="lg"
            classNames={{
              label: "text-black/50 dark:text-white/90",
              input: [
                "bg-transparent",
                "text-black/90 dark:text-white/90",
                "placeholder:text-default-700/50 dark:placeholder:text-white/60",
              ],
              innerWrapper: "bg-transparent",
              inputWrapper: [
                "shadow-xl",
                "bg-default-200/50",
                "dark:bg-default/60",
                "backdrop-blur-xl",
                "backdrop-saturate-200",
                "hover:bg-default-200/70",
                "dark:hover:bg-default/70",
                "group-data-[focused=true]:bg-default-200/50",
                "dark:group-data-[focused=true]:bg-default/60",
                "!cursor-text",
              ],
            }}
            placeholder="Type to test..."
            onChange={(e) => setInputText(e.target.value)}
            startContent={
              <SearchIcon className="text-black/50 mb-0.5 dark:text-white/90 text-slate-400 pointer-events-none flex-shrink-0" />
            }
            style={{ marginRight: "8px" }}
          />
          <Button
            color="primary"
            style={{ marginLeft: "12px" }}
            onClick={handleCheckPalindrome}
          >
            Check
          </Button>
        </div>
        <CustomModal isOpen={isOpen} onClose={handleCloseModal} size={size}>
          <p>{responseText}</p>
        </CustomModal>
      </NextUIProvider>
    </main>
  );
}
