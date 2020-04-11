"use strict";

// waiting on this fix to be released
// https://github.com/benmosher/eslint-plugin-import/issues/1285
let jsExt = [".js", ".jsx"];
let tsExt = [".ts", ".tsx"];
let ext = jsExt.concat(tsExt);

module.exports = {
  root: true,
  parser: "@typescript-eslint/parser",
  parserOptions: {
    ecmaVersion: 2018,
    sourceType: "module",
    ecmaFeatures: {
      jsx: true,
    },
    project: "./tsconfig.json",
    env: {
      es6: true,
    },
    extends: [
      "plugin:@typescript-eslint/recommended",
      "plugin:import/errors",
      "plugin:import/warnings",
      "plugin:import/typescript",
      "plugin:react/recommended",
      "prettier/@typescript-eslint",
      "prettier/react",
    ],
    plugins: ["react-hooks", "prettier"],
    rules: {
      "react/display-name": 0,
      "react/prop-types": 0,
      "react-hooks/rules-of-hooks": 2,
      "react-hooks/exhaustive-deps": 1,
      "prettier/prettier": [
        2,
        {
          singleQuote: false,
          parser: "typescript",
          semi: true,
        },
      ],
    },
    settings: {
      react: {
        version: "detect",
      },
      "import/extensions": ext,
      "import/parsers": {
        "@typescript-eslint/parser": tsExt,
      },
      "import/resolver": {
        node: {
          extensions: ext,
        },
      },
    },
  },
};
