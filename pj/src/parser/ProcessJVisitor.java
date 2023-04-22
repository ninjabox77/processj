// Generated from /Users/oswaldocisneros/Documents/pj/src/antlr/ProcessJ.g4 by ANTLR 4.12.0
package parser;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ProcessJParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 *            operations with no return type.
 */
public interface ProcessJVisitor<T> extends ParseTreeVisitor<T> {
  /**
   * Visit a parse tree produced by {@link ProcessJParser#init}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitInit(ProcessJParser.InitContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#packageDeclaration}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitPackageDeclaration(ProcessJParser.PackageDeclarationContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#importDeclaration}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitImportDeclaration(ProcessJParser.ImportDeclarationContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#singleImportDeclaration}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitSingleImportDeclaration(ProcessJParser.SingleImportDeclarationContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#multiImportDeclaration}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitMultiImportDeclaration(ProcessJParser.MultiImportDeclarationContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#multiImportDeclarationStar}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitMultiImportDeclarationStar(ProcessJParser.MultiImportDeclarationStarContext ctx);

  /**
   * Visit a parse tree produced by the {@code ProcedureDeclaration_}
   * labeled alternative in {@link ProcessJParser#typeDeclaration}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitProcedureDeclaration_(ProcessJParser.ProcedureDeclaration_Context ctx);

  /**
   * Visit a parse tree produced by the {@code RecordDeclaration_}
   * labeled alternative in {@link ProcessJParser#typeDeclaration}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitRecordDeclaration_(ProcessJParser.RecordDeclaration_Context ctx);

  /**
   * Visit a parse tree produced by the {@code ProtocolDeclaration_}
   * labeled alternative in {@link ProcessJParser#typeDeclaration}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitProtocolDeclaration_(ProcessJParser.ProtocolDeclaration_Context ctx);

  /**
   * Visit a parse tree produced by the {@code ConstantDeclaration_}
   * labeled alternative in {@link ProcessJParser#typeDeclaration}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitConstantDeclaration_(ProcessJParser.ConstantDeclaration_Context ctx);

  /**
   * Visit a parse tree produced by the {@code ExternDeclaration_}
   * labeled alternative in {@link ProcessJParser#typeDeclaration}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitExternDeclaration_(ProcessJParser.ExternDeclaration_Context ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#procedureDeclaration}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitProcedureDeclaration(ProcessJParser.ProcedureDeclarationContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#modifier}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitModifier(ProcessJParser.ModifierContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#recordDeclaration}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitRecordDeclaration(ProcessJParser.RecordDeclarationContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#extends}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitExtends(ProcessJParser.ExtendsContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#implements}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitImplements(ProcessJParser.ImplementsContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#recordBody}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitRecordBody(ProcessJParser.RecordBodyContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#recordField}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitRecordField(ProcessJParser.RecordFieldContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#protocolDeclaration}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitProtocolDeclaration(ProcessJParser.ProtocolDeclarationContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#protocolBody}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitProtocolBody(ProcessJParser.ProtocolBodyContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#protocolCase}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitProtocolCase(ProcessJParser.ProtocolCaseContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#constantDeclaration}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitConstantDeclaration(ProcessJParser.ConstantDeclarationContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#packageAccess}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitPackageAccess(ProcessJParser.PackageAccessContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#statement}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitStatement(ProcessJParser.StatementContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#localVariableDeclarationStatement}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitLocalVariableDeclarationStatement(ProcessJParser.LocalVariableDeclarationStatementContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#localVariableDeclaration}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitLocalVariableDeclaration(ProcessJParser.LocalVariableDeclarationContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#variableDeclaratorList}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitVariableDeclaratorList(ProcessJParser.VariableDeclaratorListContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#variableDeclarator}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitVariableDeclarator(ProcessJParser.VariableDeclaratorContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#variableDeclaratorIdent}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitVariableDeclaratorIdent(ProcessJParser.VariableDeclaratorIdentContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#type_}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitType_(ProcessJParser.Type_Context ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#primitiveType}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitPrimitiveType(ProcessJParser.PrimitiveTypeContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#referenceType}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitReferenceType(ProcessJParser.ReferenceTypeContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#numericType}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitNumericType(ProcessJParser.NumericTypeContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#integralType}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitIntegralType(ProcessJParser.IntegralTypeContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#floatingPointType}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitFloatingPointType(ProcessJParser.FloatingPointTypeContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#arrayType}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitArrayType(ProcessJParser.ArrayTypeContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#channelType}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitChannelType(ProcessJParser.ChannelTypeContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#typeVariable}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitTypeVariable(ProcessJParser.TypeVariableContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#dims}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitDims(ProcessJParser.DimsContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#variableInitializer}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitVariableInitializer(ProcessJParser.VariableInitializerContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#expressionStatement}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitExpressionStatement(ProcessJParser.ExpressionStatementContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#expression}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitExpression(ProcessJParser.ExpressionContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#annotation}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitAnnotation(ProcessJParser.AnnotationContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#normalAnnotation}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitNormalAnnotation(ProcessJParser.NormalAnnotationContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#elementValuePairList}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitElementValuePairList(ProcessJParser.ElementValuePairListContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#elementValuePair}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitElementValuePair(ProcessJParser.ElementValuePairContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#elementValue}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitElementValue(ProcessJParser.ElementValueContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#markerAnnotation}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitMarkerAnnotation(ProcessJParser.MarkerAnnotationContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#singleElementAnnotation}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitSingleElementAnnotation(ProcessJParser.SingleElementAnnotationContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#literalExpression}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitLiteralExpression(ProcessJParser.LiteralExpressionContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#pathExpression}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitPathExpression(ProcessJParser.PathExpressionContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#actualDeclarationList}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitActualDeclarationList(ProcessJParser.ActualDeclarationListContext ctx);

  /**
   * Visit a parse tree produced by the {@code FormalDeclaration_}
   * labeled alternative in {@link ProcessJParser#formalDeclarationList}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitFormalDeclaration_(ProcessJParser.FormalDeclaration_Context ctx);

  /**
   * Visit a parse tree produced by the {@code LastFormalDeclaration_}
   * labeled alternative in {@link ProcessJParser#formalDeclarationList}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitLastFormalDeclaration_(ProcessJParser.LastFormalDeclaration_Context ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#formalDeclarations}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitFormalDeclarations(ProcessJParser.FormalDeclarationsContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#formalDeclaration}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitFormalDeclaration(ProcessJParser.FormalDeclarationContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#variableModifier}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitVariableModifier(ProcessJParser.VariableModifierContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#lastFormalDeclaration}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitLastFormalDeclaration(ProcessJParser.LastFormalDeclarationContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#comparisonOperator}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitComparisonOperator(ProcessJParser.ComparisonOperatorContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#assignmentOperator}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitAssignmentOperator(ProcessJParser.AssignmentOperatorContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#arrayElements}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitArrayElements(ProcessJParser.ArrayElementsContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#expressionWithBlock}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitExpressionWithBlock(ProcessJParser.ExpressionWithBlockContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#blockExpression}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitBlockExpression(ProcessJParser.BlockExpressionContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#statements}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitStatements(ProcessJParser.StatementsContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#recordExpression}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitRecordExpression(ProcessJParser.RecordExpressionContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#recordExpressionList}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitRecordExpressionList(ProcessJParser.RecordExpressionListContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#protocolExpression}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitProtocolExpression(ProcessJParser.ProtocolExpressionContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#protocolExpressionList}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitProtocolExpressionList(ProcessJParser.ProtocolExpressionListContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#tagExpressionList}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitTagExpressionList(ProcessJParser.TagExpressionListContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#externalExpression}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitExternalExpression(ProcessJParser.ExternalExpressionContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#loopExpression}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitLoopExpression(ProcessJParser.LoopExpressionContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#forLoopExpression}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitForLoopExpression(ProcessJParser.ForLoopExpressionContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#forInit}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitForInit(ProcessJParser.ForInitContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#forUpdate}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitForUpdate(ProcessJParser.ForUpdateContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#statementExpressionList}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitStatementExpressionList(ProcessJParser.StatementExpressionListContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#whileLoopExpression}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitWhileLoopExpression(ProcessJParser.WhileLoopExpressionContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#doWhileLoopExpression}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitDoWhileLoopExpression(ProcessJParser.DoWhileLoopExpressionContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#infiniteLoopExpression}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitInfiniteLoopExpression(ProcessJParser.InfiniteLoopExpressionContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#ifExpression}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitIfExpression(ProcessJParser.IfExpressionContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#caseExpression}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitCaseExpression(ProcessJParser.CaseExpressionContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#caseBlockExpression}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitCaseBlockExpression(ProcessJParser.CaseBlockExpressionContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#caseBlockGroupExpression}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitCaseBlockGroupExpression(ProcessJParser.CaseBlockGroupExpressionContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#caseLabel}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitCaseLabel(ProcessJParser.CaseLabelContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#altExpression}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitAltExpression(ProcessJParser.AltExpressionContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#priAltExpression}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitPriAltExpression(ProcessJParser.PriAltExpressionContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#altBlodyExpression}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitAltBlodyExpression(ProcessJParser.AltBlodyExpressionContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#altCase}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitAltCase(ProcessJParser.AltCaseContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#guardExpression}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitGuardExpression(ProcessJParser.GuardExpressionContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#repAltLoopExpression}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitRepAltLoopExpression(ProcessJParser.RepAltLoopExpressionContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#parBlockExpression}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitParBlockExpression(ProcessJParser.ParBlockExpressionContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#barrierExpression}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitBarrierExpression(ProcessJParser.BarrierExpressionContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#externDeclaration}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitExternDeclaration(ProcessJParser.ExternDeclarationContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#externType}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitExternType(ProcessJParser.ExternTypeContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#classType}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitClassType(ProcessJParser.ClassTypeContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#typeArguments}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitTypeArguments(ProcessJParser.TypeArgumentsContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#typeArgumentList}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitTypeArgumentList(ProcessJParser.TypeArgumentListContext ctx);

  /**
   * Visit a parse tree produced by {@link ProcessJParser#typeArgument}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitTypeArgument(ProcessJParser.TypeArgumentContext ctx);
}