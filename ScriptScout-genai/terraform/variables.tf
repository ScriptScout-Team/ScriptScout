variable "aws_region" {
  type    = string
  default = "us-east-1"
}

variable "domain_name" {
  type        = string
  description = "The domain name for the AI service (e.g. ai.scriptscout.com)"
}

variable "acm_certificate_arn" {
  type        = string
  description = "The ARN of the SSL/TLS Certificate in AWS Certificate Manager (ACM) for HTTPS"
}

variable "gemini_api_key" {
  type        = string
  description = "The Gemini API key used by the Python app"
}
