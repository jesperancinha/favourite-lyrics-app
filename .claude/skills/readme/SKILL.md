---
name: Readme standards
description: Conventions to use in Readme files
---

## 1. Remove the usage of unresolved anchors

Unresolved anchors that don't translate to a link or a website should be removed.
The most important examples of this are the usage of `(#)`. This used to be a guarantee that the image would be displayed.
This is no longer necessary, and the svg icons get generated with or without the link.


### Example 1

Replace

```text
[![GitHub release](https://img.shields.io/github/release-pre/jesperancinha/your-finance-je.svg)](#)
```

with 

```text
![GitHub release](https://img.shields.io/github/release-pre/jesperancinha/your-finance-je.svg)
```

## 2. Don't use obsolete HTML properties for showing images with divs

1. Replace usages of `<div align="center">` with `<div style="text-align: center;">`
2. If the `alt` attribute is missing from the `img` node, please add it with the text of the `a` (anchor), property value of its `title` property.

### Example 1

Replace

```html
<div align="center">
      <a title="Learning JWT security using KumuluzEE — The finances of a league of the environment" href="https://itnext.io/learning-jwt-security-using-kumuluzee-the-finances-of-a-league-of-the-environment-2f541e99cc90">
     <img 
          src="./docs/images/articles.your.finance.intro.png" 
          style="width:100%;">
      </a>
</div>
```

with

```html
<div style="text-align: center;">
      <a title="Learning JWT security using KumuluzEE — The finances of a league of the environment" href="https://itnext.io/learning-jwt-security-using-kumuluzee-the-finances-of-a-league-of-the-environment-2f541e99cc90">
     <img 
          src="./docs/images/articles.your.finance.intro.png" 
          style="width:100%;" alt="Learning JWT security using KumuluzEE — The finances of a league of the environment">
      </a>
</div>

```

## 3. Remove obsolete badges

Some badges are no longer usable and don't work because some of the frameworks they used have been discontinued.
For all `.md` files, or other markdown files, please remove badges from the following list:

1. [![codebeat badge]()]() - All codebeat badges should be removed, along with any references to using it.
2. [![BCH compliance]()]() - All BCH badges should be removed, along with any references to using it.
