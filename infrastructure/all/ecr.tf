resource "aws_ecr_repository" "foo" {
  name                 = "Qualys-demo"
  image_tag_mutability = "MUTABLE"

  image_scanning_configuration {
    scan_on_push = true
  }
}